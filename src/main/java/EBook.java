import java.util.List;

public class EBook extends Book {
    private String onlineReadLink;
    private final boolean isDownloadable;
    private String downloadLink;

    public EBook(String title, List<Author> authors, String genre, String summary, String ISBN, List<String> tags, String onlineReadLink, boolean isDownloadable, String downloadLink) {
        super(title, authors, genre, summary, ISBN, tags);
        this.onlineReadLink = onlineReadLink;
        this.isDownloadable = isDownloadable;
        this.downloadLink = downloadLink;
    }

    public String getOnlineReadLink() {
        return onlineReadLink;
    }

    public void setOnlineReadLink(String onlineReadLink) {
        this.onlineReadLink = onlineReadLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) throws IllegalArgumentException {
        if (!isDownloadable) {
            throw new IllegalArgumentException("Download is not allowed for this eBook.");
        }
        this.downloadLink = downloadLink;
    }

    @Override
    public int getLoanDuration() {
        return 14;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }
}
