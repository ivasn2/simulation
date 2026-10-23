    package entity;

    import map.WorldMap;

    public abstract class Creature extends Entity {

        private int health;
        private final int maxHealth;
        private int speed;

        public abstract void makeMove(WorldMap worldMap);

        public Creature(String symbol, int hp, int speed) {
            super(symbol);
            this.health = hp;
            this.maxHealth = health;
            this.speed = speed;
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

        public void setHealth(int health) {
            this.health = health;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public boolean starve(WorldMap worldMap) {
           int newHealth = getHealth() - 10;
           if (newHealth <= 0) {
               worldMap.removeEntity(getCoordinates());
               return false;
           }
           setHealth(newHealth);
           return true;
        }
    }
