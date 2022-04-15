package se.chalmers.topdesk.model;

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
}
