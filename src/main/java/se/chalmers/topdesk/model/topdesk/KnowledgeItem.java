package se.chalmers.topdesk.model.topdesk;

import se.chalmers.topdesk.Configuration;

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

    //TODO public String getItemAsHtml()

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