package world;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;


public class Slingshot extends Walker implements StepListener{


    private static final Shape slingshotBody = new PolygonShape(-0.26f,0.83f, 0.43f,-0.03f, 0.03f,-0.64f, -0.64f,-0.61f, -0.74f,0.05f
    );
    private static final String rightFacingURL = "data/SlingshotRight.png";
    private static final String leftFacingURL = "data/SlingshotLeft.png";

    private BodyImage slingshotImage;
    private boolean rightFacing;
    private float x, y;
    private World world;

    private int score;

    private int lives;


    public Slingshot(World w, float x, float y, boolean rightFacing) {
        super(w, slingshotBody);
        this.rightFacing = rightFacing;
        this.x = x;
        this.y = y;
        this.world = w;
        score = 0;
        lives = 3;

        SlingshotHit slingshotHit = new SlingshotHit(this);
        this.addCollisionListener(slingshotHit);

        this.setPosition(new Vec2(x, y));

        world.addStepListener(this);
        this.setName("Slingshot");
    }

    public boolean isRightFacing() {
        return rightFacing;
    }

    public void switchRightFacing() {
        this.rightFacing = !this.rightFacing;
    }


    public void setImage() {
        if (this.rightFacing) {
            slingshotImage = new BodyImage(rightFacingURL, 4f);
        } else {
            slingshotImage = new BodyImage(leftFacingURL, 4f);
        }
        this.addImage(slingshotImage);
    }

    public void shootingImage() {
        BodyImage shootingImage;
        if (this.rightFacing) {
             shootingImage = new BodyImage("data/ShootRight.png", 3f);
        } else {
            shootingImage = new BodyImage("data/ShootLeft.png", 3f);
        }
        this.removeAllImages();
        this.addImage(shootingImage);
    }

    public void shoot() {

        Shape rockShape = new PolygonShape(0.032f,0.228f, 0.108f,-0.06f, -0.12f,-0.044f, -0.1f,0.188f);
        BodyImage rockImage = new BodyImage("data/Stone.png", 2);
        DynamicBody rock = new DynamicBody(world, rockShape);

        StoneCollision stoneHits = new StoneCollision(this);
        rock.addCollisionListener(stoneHits);
        rock.setName("Stone");


        rock.setGravityScale(1.5f);

        if (this.rightFacing) {
            rock.setPosition(new Vec2(this.getPosition().x + 1, this.getPosition().y));
            rock.addImage(rockImage);
            rock.setLinearVelocity(new Vec2(5, 5));
        } else {
            rock.setPosition(new Vec2(this.getPosition().x - 1, this.getPosition().y));
            rock.addImage(rockImage);
            rock.setLinearVelocity(new Vec2(-5, 5));
        }


    }

    public float getLives() {
        return this.lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int addition) {
        this.score += addition;
    }

    public void decrementLives(float life) {
        this.lives -= life;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (this.lives <= 0) {
            this.destroy();
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if (this.getPosition().y < -20) {
            this.setPosition(new Vec2(0,-13));
        }





    }

    public class SlingshotHit implements CollisionListener {

        private Slingshot slingshotBoy;
        private Sound sound;

        public SlingshotHit(Slingshot slingshotBoy) {
            this.slingshotBoy = slingshotBoy;
            this.sound = new Sound();
        }


        @Override
        public void collide(CollisionEvent collisionEvent) {
            if (collisionEvent.getOtherBody() instanceof Dragon) {
                this.slingshotBoy.decrementLives(1);
            }
            if ((collisionEvent.getOtherBody().getName() ==  "BlueKnight") ||
            collisionEvent.getOtherBody().getName() ==  "RedKnight") {
                this.slingshotBoy.decrementLives(0.5f);
            }
            if (collisionEvent.getOtherBody().getName() ==  "Collectible") {
                this.slingshotBoy.addScore(10);
                collisionEvent.getOtherBody().destroy();
                this.sound.setFile("Coin");
                this.sound.play();
            }
            if (collisionEvent.getOtherBody().getName() ==  "Fireball") {
                this.slingshotBoy.decrementLives(1f);
            }
        }
    }
}
