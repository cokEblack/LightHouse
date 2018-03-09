package area52;

import breakout.game.Game;
import breakout.game.api.Ability;
import breakout.game.api.Buff;
import breakout.game.api.GameObject;
import breakout.game.gameobject.AbstractBuff;
import breakout.game.gameobject.GameplayAttribute;
import breakout.game.texture.ImageTexture;
import breakout.game.texture.Texture;
import lighthouse.LighthouseGameObjectProperty;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * The {@code QuenAbility} is casted on the caster itself and applies a
 * {@code QuenBuff} which makes the target invulnerable to any damage
 * dealt to it.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 */
public class QuenAbility implements Ability {

    /**
     * The {@code QuenBuff} temporarily changes the invulnerability
     * and makes it immune to any damage.
     *
     */
    private class QuenBuff extends AbstractBuff {

        /** The default fill color for the buff's texture */
        private final Color TEXTURE_COLOR = new Color(0xdaa520);

        /** The fill color which was previously set for the game object */
        private Color previousLighthouseFillColor;

        /** The texture which was previously set for the game object */
        private Texture previousTexture;

        /** The texture of the target, when the buff is applied */
        private Texture texture;

        /** The buff's target */
        private final GameObject target;

        /**
         * Creates a {@code QuenBuff} which is bound to a specific target.
         *
         * @param target A target
         */
        public QuenBuff(final GameObject target) {

            super(3000);

            this.target = target;

            // TODO Refactor to method
            // Any target generically changes its texture, when the buff
            // is applied.
            BufferedImage buffImage = new BufferedImage(
                (int) target.getBody().getWidth(),
                (int) target.getBody().getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR
            );

            Graphics2D g = (Graphics2D) buffImage.getGraphics();
            g.setColor(TEXTURE_COLOR);
            g.fillRect(0,0, buffImage.getWidth(), buffImage.getHeight());
            g.dispose();

            texture = new ImageTexture(buffImage);

        }

        @Override
        public void apply(final GameObject target) {

            if (target == null) {
                throw new IllegalArgumentException("The target must not be null.");
            }

            assert target == this.target : "The target is invalid. This buff can only be applied to the caster itself.";

            target.setVulnerability(false);

            // Change the game object's texture
            previousTexture = target.getTexture();
            target.setTexture(texture);
            previousLighthouseFillColor = (Color) target.getData(LighthouseGameObjectProperty.LIGHTHOUSE_FILL_COLOR);
            target.setData(LighthouseGameObjectProperty.LIGHTHOUSE_FILL_COLOR, TEXTURE_COLOR);

            Logger.getLogger(Game.class.getName()).info(() ->
                String.format("%s has been applied to %s.", getClass().getSimpleName(), target.getName())
            );

        }

        @Override
        public void remove(final GameObject target) {

            if (target == null) {
                throw new IllegalArgumentException("The target must not be null.");
            }

            assert target == this.target : "The target is invalid. This buff can only be applied to the caster itself.";

            target.setVulnerability(true);

            // Change the game object's texture to the previous texture
            target.setTexture(previousTexture);
            target.setData(LighthouseGameObjectProperty.LIGHTHOUSE_FILL_COLOR, previousLighthouseFillColor);

            Logger.getLogger(Game.class.getName()).info(() ->
                    String.format("%s has been remove from %s.", getClass().getSimpleName(), target.getName())
            );

        }

    }

    /** The duration that this ability needs to cool down */
    public static final long COOLDOWN_DURATION = 10 * 1000;

    /** The caster of this ability */
    private final GameObject caster;

    /** The buff that is applied by this ability */
    private final Buff quenBuff;

    /** Whether this ability is cooling down or not */
    private boolean isCoolingDown;

    /**
     * Creates a {@code QuenAbility} which is bound to a caster.
     *
     * This ability can only be casted on the caster itself.
     *
     * @param caster The caster of this ability.
     * @throws IllegalArgumentException If the caster is {@code null}
     *
     */
    public QuenAbility(GameObject caster) {

        if (caster == null) {
            throw new IllegalArgumentException("The caster must not be null.");
        }

        this.caster = caster;
        quenBuff = new QuenBuff(caster);
    }

    /**
     * Applies a {@code QuenBuff} to the target which makes the target
     * invulnerable for a set duration.
     *
     * This ability can only be casted on the caster itself.
     *
     * This method does not throw a {@code IllegalArgumentException}, if the
     * provided target is {@code null}. It is silently assumed that the owner
     * of this ability is always the target of this ability. The target gets
     * corrected, if there is a different target passed to this method.
     *
     * @param target A target
     *
     */
    @Override
    public void apply(GameObject target) {

        // TODO Throw CooldownException to allow consequences like notifications
        if (isCoolingDown) return;

        // The ability can only be casted on itself
        if (target != caster) {
            target = caster;
        }

        // This ability costs 3 Adrenaline points
        GameplayAttribute attribute = target.getAttribute(Adrenaline.class);
        if (attribute.getCurrentValue() < 3) return;
        attribute.setCurrentValue(attribute.getCurrentValue() - 3);

        target.applyBuff(quenBuff);

        isCoolingDown = true;

        // After this timer expires, the ability is available for casting again
        Timer cooldownTimer = new Timer();
        cooldownTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                isCoolingDown = false;
            }

        }, COOLDOWN_DURATION);

    }

}
