class TextState {
    String text;
    TextState next;
    TextState prev;

    public TextState(String text) {
        this.text = text;
        this.next = null;
        this.prev = null;
    }
}

class TextEditor {
    private TextState head;
    private TextState tail;
    private TextState currentState;
    private final int historySizeLimit = 10;
    private int historySize = 0;

    public TextEditor() {
        this.head = null;
        this.tail = null;
        this.currentState = null;
    }

    public void addState(String text) {
        TextState newState = new TextState(text);
        if (head == null) {
            head = newState;
            tail = newState;
            currentState = newState;
        } else {
            newState.prev = currentState;
            currentState.next = newState;
            currentState = newState;
            tail = newState;
            historySize++;
            if (historySize > historySizeLimit) {
                head = head.next;
                if (head != null) {
                    head.prev = null;
                }
                historySize--;
            }
        }
    }

    public void undo() {
        if (currentState != null && currentState.prev != null) {
            currentState = currentState.prev;
        }
    }

    public void redo() {
        if (currentState != null && currentState.next != null) {
            currentState = currentState.next;
        }
    }

    public String getCurrentText() {
        if (currentState == null) {
            return "";
        }
        return currentState.text;
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.addState("");
        System.out.println("Current: " + editor.getCurrentText());

        editor.addState("Hello");
        System.out.println("Current: " + editor.getCurrentText());

        editor.addState("Hello World");
        System.out.println("Current: " + editor.getCurrentText());

        editor.undo();
        System.out.println("Undo: " + editor.getCurrentText());

        editor.redo();
        System.out.println("Redo: " + editor.getCurrentText());

        editor.undo();
        editor.undo();
        System.out.println("Undo x2: " + editor.getCurrentText());

        editor.redo();
        System.out.println("Redo: " + editor.getCurrentText());

        editor.addState("Hello Universe");
        System.out.println("Current: " + editor.getCurrentText());

        editor.undo();
        System.out.println("Undo: " + editor.getCurrentText());

        for (int i = 0; i < 15; i++) {
            editor.addState("State " + i);
        }
        System.out.println("Current after adding many states: " + editor.getCurrentText());
        for (int i = 0; i < 12; i++) {
            editor.undo();
        }
        System.out.println("Undo x12: " + editor.getCurrentText());
        for (int i = 0; i < 5; i++) {
            editor.redo();
        }
        System.out.println("Redo x5: " + editor.getCurrentText());
    }
}