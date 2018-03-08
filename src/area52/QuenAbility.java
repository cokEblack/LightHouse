package area52;

import breakout.game.Game;
import breakout.game.api.Ability;
import breakout.game.api.Buff;
import breakout.game.api.GameObject;
import breakout.game.texture.ImageTexture;
import breakout.game.texture.Texture;
import lighthouse.LighthouseGameObjectProperty;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class QuenAbility implements Ability {

    private class QuenBuff implements Buff {

        private Color previousLighthouseFillColor;
        private Color textureColor = new Color(0xdaa520);
        private Texture previousTexture;
        private Texture texture;
        private GameObject target;

        public QuenBuff(GameObject target) {

            this.target = target;

            BufferedImage buffImage = new BufferedImage(
                (int) target.getBody().getWidth(),
                (int) target.getBody().getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR
            );

            Graphics2D g = (Graphics2D) buffImage.getGraphics();
            g.setColor(textureColor);
            g.fillRect(0,0, buffImage.getWidth(), buffImage.getHeight());

            texture = new ImageTexture(buffImage);

        }

        @Override
        public long getDuration() {
            return 3000;
        }

        @Override
        public void apply(GameObject target) {

            target.setVulnerability(false);

            previousTexture = target.getTexture();
            previousLighthouseFillColor = (Color) target.getData(LighthouseGameObjectProperty.LIGHTHOUSE_FILL_COLOR);
            target.setTexture(texture);
            target.setData(LighthouseGameObjectProperty.LIGHTHOUSE_FILL_COLOR, textureColor);

            Logger.getLogger(Game.class.getName()).info(() ->
                String.format("%s has been applied to %s.", getClass().getSimpleName(), target.getName())
            );

        }

        @Override
        public void remove(GameObject target) {

            target.setVulnerability(true);

            target.setTexture(previousTexture);
            target.setData(LighthouseGameObjectProperty.LIGHTHOUSE_FILL_COLOR, previousLighthouseFillColor);

            Logger.getLogger(Game.class.getName()).info(() ->
                    String.format("%s has been remove from %s.", getClass().getSimpleName(), target.getName())
            );

        }

    }

    private final long COOLDOWN_DURATION = 10 * 1000;
    private final GameObject caster;
    private final Buff quenBuff;
    private boolean isCoolingDown;

    public QuenAbility(GameObject caster) {
        this.caster = caster;
        quenBuff = new QuenBuff(caster);
    }

    @Override
    public void apply(GameObject target) {

        if (isCoolingDown) return;

        // The ability can only be casted on itself
        if (target != caster) {
            target = caster;
        }

        target.applyBuff(quenBuff);
        isCoolingDown = true;

        Timer cooldownTimer = new Timer();
        cooldownTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                isCoolingDown = false;
            }

        }, COOLDOWN_DURATION);

    }

}
