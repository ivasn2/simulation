    package entity;

    import map.Coordinates;
    import map.WorldMap;
    import simulation.BreadthFirstSearch;

    import java.util.List;

    public abstract class Creature extends Entity {

        private int health;
        private final int maxHealth;
        private final int speed;
        private final int starvationDamage;

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

        public boolean canReproduce() {
            return this.health > this.maxHealth / 2;
        }

        public void takeDamage(int damage) {
            this.health -= damage;
        }

        public boolean isDead() {
            return this.health <= 0;
        }

        public abstract Class<? extends Entity> getTargetClass();

        public abstract void interact(Entity target, WorldMap worldMap, List<Coordinates> path);

        public void makeMove(WorldMap worldMap) {

            takeDamage(getStarvationDamage());

            if (isDead()) {
                worldMap.removeEntity(getCoordinates());
                return;
            }

            Coordinates oldCoordinates = getCoordinates();
            List<Coordinates> path = BreadthFirstSearch.findPath(getCoordinates(), worldMap, getTargetClass());

            if (!path.isEmpty()) {
                int stepsToTake = Math.min(path.size(), getSpeed());
                Coordinates targetCell = path.get(stepsToTake - 1);
                if (targetCell.equals(path.getLast())) {
                    interact(worldMap.getEntity(targetCell), worldMap, path);
                } else {
                    worldMap.removeEntity(oldCoordinates);
                    worldMap.addEntity(targetCell, this);}
            }
        }
    }
