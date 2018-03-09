package area52;

import breakout.game.api.*;
import breakout.game.gameobject.AbstractBuff;
import breakout.game.gameobject.AbstractGameObject;
import breakout.game.gameobject.GameObjectBody;
import breakout.game.gameobject.GenericWeapon;
import breakout.game.state.GameState;
import breakout.game.texture.ImageTexture;
import newton.geometry.Circle;
import newton.geometry.Vector;
import newton.physics.Body;
import newton.physics.collision.CollisionEvent;
import newton.physics.collision.CollisionListener;

import java.awt.*;
import java.awt.image.BufferedImage;

import static area52.Area52GameObjectProperty.AREA52_IGNI_PARTICLE_HIT_COUNT;

/**
 * The {@code IgniAbility} emits a deadly fire wave which is ignites its targets.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class IgniAbility implements Ability {

    /**
     * The {@code IgniteBuff} sets a target on fire which raises their damage.
     *
     */
    private class IgniteBuff extends AbstractBuff {

        /** Set previous weapon used by the target */
        private Weapon previousWeapon;

        /**
         * Creates an {@code IgniteBuff} which endures 4 seconds.
         *
         */
        public IgniteBuff() {
            super(4000);
        }

        @Override
        public void apply(GameObject target) {
            previousWeapon = target.getWeapon();
            Weapon igniteWeapon = new GenericWeapon(previousWeapon.getDamage() + 1);
            target.setWeapon(igniteWeapon);
        }

        @Override
        public void remove(GameObject target) {
            target.setWeapon(previousWeapon);
        }
    }

    /**
     * The {@code FireParticle} is part of the abilities animation and deals the
     * damage, when the particle hits a body.
     *
     */
    private class FireParticle extends AbstractGameObject {

        {

            int randomRadius = (int) Math.floor(Math.random() * 2) + 2;

            setBody(new GameObjectBody(this, new Body(new Circle(0, 0, randomRadius), 1)));

            // Randomize the size and color of the particles
            BufferedImage image = new BufferedImage(2 * randomRadius, 2 * randomRadius, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics g = image.getGraphics();

            // Random red tone for a more visually appealing fire effect
            float h = (float) Math.random() * 0.1f;
            float s = (float) Math.random() * 0.1f + 0.8f;
            float b = (float) Math.random() * 0.1f + 0.8f;
            g.setColor(Color.getHSBColor(h, s, b));

            g.fillArc(0, 0,2 *  randomRadius, 2 * randomRadius, 0, 360);
            g.dispose();

            setTexture(new ImageTexture(image));

            // Set the particles damage
            setWeapon(new GenericWeapon(0.025f));

            getBody().addCollisionListener(new CollisionListener() {

                @Override
                public void onCollision(CollisionEvent event) {

                    // The particles gets destroyed, it does not matter which target it hits
                    destroy();

                    if (!(event.getSource() instanceof GameObjectBody) || !(event.getTarget() instanceof GameObjectBody)) return;

                    GameObject particle = ((GameObjectBody) event.getSource()).getGameObject();
                    GameObject target = ((GameObjectBody) event.getTarget()).getGameObject();

                    // The particle do significantly less damage against bricks
                    if (target instanceof Brick) {
                        target.damage(particle.getWeapon().getDamage() * 0.25f);
                    }

                    // Each 10 particles hits the ball gets ignited, which increases the balls
                    // damage output
                    if (target instanceof Ball) {

                        if (target.getData(AREA52_IGNI_PARTICLE_HIT_COUNT) == null) {
                            target.setData(AREA52_IGNI_PARTICLE_HIT_COUNT, 0);
                        }

                        int hitCount = (int) target.getData(AREA52_IGNI_PARTICLE_HIT_COUNT);
                        hitCount++;
                        if (hitCount % 10 == 0) {
                            target.applyBuff(new IgniteBuff());
                        }

                        target.setData(AREA52_IGNI_PARTICLE_HIT_COUNT, hitCount);

                    }

                }

            });

        }

    }

    /** The caster of this ability */
    private final GameObject caster;

    /** The game state which is accessed to emit fire particles */
    private final GameState state;

    /**
     * Creates an {@code IgniAbility} with a caster and the game state.
     *
     * The game state is used to emit the fire particles.
     *
     * @param caster A caster
     * @param state A game state
     */
    public IgniAbility(GameObject caster, GameState state) {

        this.caster = caster;
        this.state = state;

    }

    @Override
    public void apply(GameObject target) {

        if (target.isDestroyed()) return;

        Adrenaline adrenaline = (Adrenaline) caster.getAttribute(Adrenaline.class);

        // This ability costs 0.05 adrenaline points per tick
        if (adrenaline.getCurrentValue() < 0.05f) return;

        adrenaline.setCurrentValue(adrenaline.getCurrentValue() - 0.05f);

        // Emit 3 particles on each tick
        for (int i = 0, n = 3; i < n; i++) {
            FireParticle p = new FireParticle();
            Circle c = (Circle) p.getBody().getShape();
            c.setCenter(state.getMouse().getPosition());
            p.getBody().setVelocity(generateRandomVelocity());
            state.addGameObject(p);
        }

    }

    /**
     * Generates a random velocity.
     *
     * This method is used to create the spray effect.
     *
     * @return A random velocity
     */
    private Vector generateRandomVelocity() {
        float vx = (float) (0.5f * Math.random() - 0.25f);
        float vy = -0.5f;
        return new Vector(vx, vy);
    }

}
