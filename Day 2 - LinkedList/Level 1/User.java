import java.util.LinkedList;

class User {
    int userId;
    String name;
    int age;
    LinkedList<Integer> friendIds;
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new LinkedList<>();
        this.next = null;
    }

    public void addFriend(int friendId) {
        if (!friendIds.contains(friendId)) {
            friendIds.add(friendId);
        }
    }

    public void removeFriend(Integer friendId) {
        friendIds.remove(friendId);
    }

    public LinkedList<Integer> getFriendIds() {
        return friendIds;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
}

class SocialNetwork {
    User head;

    public SocialNetwork() {
        this.head = null;
    }

    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
            return;
        }
        User current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newUser;
    }

    public User findUser(int userId) {
        User current = head;
        while (current != null) {
            if (current.userId == userId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public User findUser(String name) {
        User current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void addFriendConnection(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 != null && user2 != null) {
            user1.addFriend(userId2);
            user2.addFriend(userId1);
        }
    }

    public void removeFriendConnection(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 != null && user2 != null) {
            user1.removeFriend(userId2);
            user2.removeFriend(userId1);
        }
    }

    public void findMutualFriends(int userId1, int userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 != null && user2 != null) {
            LinkedList<Integer> friends1 = user1.getFriendIds();
            LinkedList<Integer> friends2 = user2.getFriendIds();
            System.out.println("Mutual friends between User " + userId1 + " and User " + userId2 + ":");
            for (int friendId : friends1) {
                if (friends2.contains(friendId)) {
                    System.out.println(friendId);
                }
            }
        } else {
            System.out.println("One or both users not found.");
        }
    }

    public void displayFriends(int userId) {
        User user = findUser(userId);
        if (user != null) {
            System.out.println("Friends of User " + userId + " (" + user.getName() + "):");
            for (int friendId : user.getFriendIds()) {
                System.out.println(friendId);
            }
        } else {
            System.out.println("User " + userId + " not found.");
        }
    }

    public void searchUserById(int userId) {
        User user = findUser(userId);
        if (user != null) {
            System.out.println("User ID: " + user.userId);
            System.out.println("Name: " + user.name);
            System.out.println("Age: " + user.age);
            System.out.println("Friends: " + user.getFriendIds());
        } else {
            System.out.println("User with ID " + userId + " not found.");
        }
    }

    public void searchUserByName(String name) {
        User user = findUser(name);
        if (user != null) {
            System.out.println("User ID: " + user.userId);
            System.out.println("Name: " + user.name);
            System.out.println("Age: " + user.age);
            System.out.println("Friends: " + user.getFriendIds());
        } else {
            System.out.println("User with name '" + name + "' not found.");
        }
    }

    public int countFriends(int userId) {
        User user = findUser(userId);
        if (user != null) {
            return user.getFriendIds().size();
        }
        return 0;
    }

    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();
        network.addUser(1, "Alice", 25);
        network.addUser(2, "Bob", 30);
        network.addUser(3, "Charlie", 22);
        network.addUser(4, "David", 28);

        network.addFriendConnection(1, 2);
        network.addFriendConnection(1, 3);
        network.addFriendConnection(2, 3);
        network.addFriendConnection(3, 4);

        network.displayFriends(1);
        network.displayFriends(2);
        network.displayFriends(3);
        network.displayFriends(4);

        network.findMutualFriends(1, 2);
        network.findMutualFriends(1, 4);

        network.searchUserById(3);
        network.searchUserByName("bob");

        System.out.println("Number of friends for Alice: " + network.countFriends(1));
        System.out.println("Number of friends for David: " + network.countFriends(4));

        network.removeFriendConnection(1, 3);
        System.out.println("Friends of Alice after removing Charlie:");
        network.displayFriends(1);
        System.out.println("Number of friends for Alice: " + network.countFriends(1));
    }
}