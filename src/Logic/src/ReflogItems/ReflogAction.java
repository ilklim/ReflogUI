package ReflogItems;

public enum ReflogAction {
    COMMIT("commit"),
    INITIAL_COMMIT("commit (initial)"),
    AMENDING_COMMIT("commit (amend)"),
    CHECKOUT("checkout"),
    MERGE("merge"),
    ERROR("ERROR"),
    REBASE("rebase"),
    INTERACTIVE_REBASE_PICK("rebase -i (pick)"),
    INTERACTIVE_REBASE_EDIT("rebase -i (edit)"),
    INTERACTIVE_REBASE_FINISH("rebase -i (finish)"),
    REBASE_FINISHED("rebase finished"),
    RESET("reset"),
    CHERRY_PICK("cherry-pick"),
    PULL_REBASE("pull --rebase"),
    PULL("pull");
    private final String s;
    private ReflogAction(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }
}

