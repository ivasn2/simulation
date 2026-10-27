    package entity;

    import map.WorldMap;

    public abstract class Creature extends Entity {

        private int health;
        private final int maxHealth;
        private int speed;
        private final int starvationDamage;

        public abstract void makeMove(WorldMap worldMap);

        public Creature(String symbol, int hp, int speed, int starvationDamage) {
            super(symbol);
            this.health = hp;
            this.maxHealth = health;
            this.speed = speed;
            this.starvationDamage = starvationDamage;
        }

        public int getHealth() {
            return health;
        }

        public int getMaxHealth() {
            return maxHealth;
        }

        public int getSpeed() {
            return speed;
        }

        public int getStarvationDamage() {
            return starvationDamage;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public boolean canReproduce() {
            return this.health > this.maxHealth / 2;
        }

        public void takeDamage(int damage) {
            this.health -= damage;
        }

        public boolean isDead() {
            return this.health <= 0;
        }
    }
