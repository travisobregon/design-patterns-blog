package app.tutorials;

public class TutorialFactory extends AbstractTutorialFactory {
    @Override
    public Tutorial createVideoTutorial() {
        return new VideoTutorial();
    }

    @Override
    public Tutorial createBlogTutorial() {
        return new BlogTutorial();
    }
}
