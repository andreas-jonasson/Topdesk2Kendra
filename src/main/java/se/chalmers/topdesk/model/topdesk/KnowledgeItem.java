package se.chalmers.topdesk.model.topdesk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import se.chalmers.topdesk.Configuration;
import se.chalmers.topdesk.model.kendra.Metadata;
import se.chalmers.topdesk.model.kendra.MetadataAttributes;

import java.util.Objects;

public class KnowledgeItem
{
    public String id;
    public String number;
    public KnowledgeItemParent parent;
    public KnowledgeItemTranslation translation;
    public KnowledgeItemVisibility visibility;
    public KnowledgeItemStatus status;
    public KnowledgeItemManager manager;
    public KnowledgeItemExternalLink externalLink;

    public String getContent()
    {
        return translation.content.content;
    }

    public String getCreationDate()
    {
        return translation.creationDate;
    }

    public String getModificationDate()
    {
        return translation.modificationDate;
    }

    public String getAsHtml()
    {

        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<title>" + getDocumentTitle() + "</title>\n" +
                "<body>\n" +
                "\t" + getDocumentBody() + "\n" +
                "</body>\n" +
                "</html>\n";
    }

    public String getMetadataJson()
    {
        Metadata metadata = new Metadata(getDocumentId(), getMetadataAttributes(), null, getDocumentTitle(), getContent());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(metadata);
    }

    private MetadataAttributes getMetadataAttributes()
    {
        MetadataAttributes metadataAttributes = new MetadataAttributes();

        if (translation.creationDate != null)
            metadataAttributes._created_at = translation.creationDate;

        metadataAttributes._data_source_id = "TopDesk";

        if (translation.content.content != null)
            metadataAttributes._document_body = translation.content.content;

        if (getDocumentId() != null)
            metadataAttributes._document_id = getDocumentId();

        metadataAttributes._file_type = "HTML";
        metadataAttributes._language_code = getLanguageCode();

        if (getModificationDate() != null)
            metadataAttributes._last_updated_at = getModificationDate();

        return metadataAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeItem)) return false;
        KnowledgeItem that = (KnowledgeItem) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(parent, that.parent) && Objects.equals(translation, that.translation) && Objects.equals(visibility, that.visibility) && Objects.equals(status, that.status) && Objects.equals(manager, that.manager) && Objects.equals(externalLink, that.externalLink);
    }

    public String getDataSource()
    {
        return "TopDesk";
    }

    public String getDocumentBody()
    {
        return replaceWithAbsoluteUrl(translation.content.content, Configuration.getInstance().topdesk_base_url);
    }

    private String replaceWithAbsoluteUrl(String body, String baseUrl)
    {
        return body.replaceAll("/tas/secure/mango/image", baseUrl + "/tas/secure/mango/image");
    }

    public String getDocumentTitle()
    {
        return translation.content.title;
    }

    public String getDocumentId()
    {
        if (number != null)
            return number;
        return id;
    }

    public String getDocumentType()
    {
        return "HTML";
    }

    public String getLanguageCode()
    {
        if (translation.language == null)
            return Configuration.getInstance().topdesk_default_language;

        return translation.language;
    }

    @Override
    public String toString() {
        return "KnowledgeItem{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", parent=" + parent +
                ", translation=" + translation +
                ", visibility=" + visibility +
                ", status=" + status +
                ", manager=" + manager +
                ", externalLink=" + externalLink +
                '}';
    }
}
