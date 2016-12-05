package app.tutorials;

public class TutorialCounter implements Tutorial {
    private Tutorial tutorial;
    private static int numberOfTutorials;

    public TutorialCounter(Tutorial tutorial) {
        this.tutorial = tutorial;
        numberOfTutorials = 0;
    }

    @Override
    public void add(String... properties) {
        this.tutorial.add(properties);
        numberOfTutorials++;
    }

    public static int getNumberOfTutorials() {
        return numberOfTutorials;
    }
}
