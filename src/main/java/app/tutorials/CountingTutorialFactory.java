package app.tutorials;

public class CountingTutorialFactory extends AbstractTutorialFactory {
    @Override
    public Tutorial createVideoTutorial() {
        return new TutorialCounter(new VideoTutorial());
    }

    @Override
    public Tutorial createBlogTutorial() {
        return new TutorialCounter(new BlogTutorial());
    }
}
