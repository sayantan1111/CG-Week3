class CircularTour {
    static class PetrolPump {
        int petrol;
        int distance;

        public PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }

    public static int findStartingPoint(PetrolPump[] pumps) {
        int n = pumps.length;
        for (int start = 0; start < n; start++) {
            int currentPetrol = 0;
            int count = 0;
            int current = start;
            boolean possible = true;

            while (count < n) {
                currentPetrol += pumps[current].petrol;
                if (currentPetrol < pumps[current].distance) {
                    possible = false;
                    break;
                }
                currentPetrol -= pumps[current].distance;
                current = (current + 1) % n;
                count++;
            }

            if (possible) {
                return start;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        PetrolPump[] pumps = {
                new PetrolPump(4, 6),
                new PetrolPump(6, 5),
                new PetrolPump(7, 3)
        };
        int start = findStartingPoint(pumps);
        if (start != -1) {
            System.out.println("Start tour at petrol pump " + start);
        } else {
            System.out.println("No circular tour possible");
        }

        PetrolPump[] pumps2 = {
                new PetrolPump(6, 4),
                new PetrolPump(3, 6),
                new PetrolPump(7, 3)
        };
        int start2 = findStartingPoint(pumps2);
        if (start2 != -1) {
            System.out.println("Start tour at petrol pump " + start2);
        } else {
            System.out.println("No circular tour possible");
        }
    }
}