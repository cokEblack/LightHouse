package area52;

import breakout.game.Game;
import breakout.game.api.Ability;
import breakout.game.api.Buff;
import breakout.game.api.GameObject;
import breakout.game.gameobject.AbstractBuff;
import breakout.game.gameobject.GameplayAttribute;
import breakout.game.gameobject.Health;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * The {@code DrinkSwallowAbility} is casted on the caster itself and applies
 * {@code SwallowBuff} which temporarily increases the health regeneration rate.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class DrinkSwallowAbility implements Ability {

    /**
     * The {@code SwallowBuff} temporarily changes the health regeneration rate.
     *
     */
    private class SwallowBuff extends AbstractBuff {

        /** The additional health regeneration rate that is granted by this buff */
        private float additionalHealthRegeneration = 0.0004f;

        /** The buff's target */
        private GameObject target;

        /**
         * Creates a {@code SwallowBuff} which is bound to a specific target.
         *
         * @param target A target
         */
        public SwallowBuff(GameObject target) {
            super(8000);
            this.target = target;
        }

        @Override
        public void apply(GameObject target) {

            if (target == null) {
                throw new IllegalArgumentException("The target must not be null.");
            }

            assert target == this.target : "The target is invalid. This buff can only be applied to the caster itself.";

            // Increase the health regeneration rate
            Health health = target.getHealth();
            health.setRegenerationRate(
                    health.getRegenerationRate() + additionalHealthRegeneration
            );

            Logger.getLogger(Game.class.getName()).info(() ->
                    String.format("%s has been applied to %s.", getClass().getSimpleName(), target.getName())
            );

        }

        @Override
        public void remove(GameObject target) {

            if (target == null) {
                throw new IllegalArgumentException("The target must not be null.");
            }

            assert target == this.target : "The target is invalid. This buff can only be applied to the caster itself.";

            // Decrease the health regeneration rate
            Health health = target.getHealth();
            health.setRegenerationRate(
                    health.getRegenerationRate() - additionalHealthRegeneration
            );

            Logger.getLogger(Game.class.getName()).info(() ->
                    String.format("%s has been remove from %s.", getClass().getSimpleName(), target.getName())
            );

        }

    }

    /** The duration that this ability needs to cool down */
    public final long COOLDOWN_DURATION = 15 * 1000;

    /** The caster of this ability */
    private final GameObject caster;

    /** The buff that is applied by this ability */
    private final Buff swallowBuff;

    /** Whether this ability is cooling down or not */
    private boolean isCoolingDown;

    /**
     * Creates a {@code DrinkSwallowAbility} which is bound to a caster.
     *
     * This ability can only be casted on the caster itself.
     *
     * @param caster The caster of this ability
     * @throws IllegalArgumentException If the caster is {@code null}
     *
     */
    public DrinkSwallowAbility(GameObject caster) {

        if (caster == null) {
            throw new IllegalArgumentException("The caster must not be null.");
        }

        this.caster = caster;
        this.swallowBuff = new SwallowBuff(caster);

    }

    /**
     * Applies a {@code SwallowBuff} to the target which temporarily increases
     * the health regeneration rate.
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

        // This ability costs 8 Adrenaline points
        GameplayAttribute attribute = target.getAttribute(Adrenaline.class);
        if (attribute.getCurrentValue() < 8) return;
        attribute.setCurrentValue(attribute.getCurrentValue() - 8);

        target.applyBuff(swallowBuff);

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
