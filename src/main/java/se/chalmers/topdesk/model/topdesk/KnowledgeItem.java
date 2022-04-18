package se.chalmers.topdesk.model.topdesk;

import se.chalmers.topdesk.Configuration;
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
        StringBuilder builder = new StringBuilder();

        builder.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                    .append("\t<title>" + getDocumentTitle() + "</title>\n")
                .append("<body>\n")
                    .append("\t" + getDocumentBody() + "\n")
                .append("</body>\n")
                .append("</html>\n");

        return builder.toString();
    }

    public String getMetadataJson()
    {
        MetadataAttributes metadataAttributes = new MetadataAttributes();

        if (translation.creationDate != null)
            metadataAttributes._created_at = translation.creationDate;
        // TODO You are here...
        return "";
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
        return translation.content.content;
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
