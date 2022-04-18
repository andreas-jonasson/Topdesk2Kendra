package se.chalmers.topdesk.model.topdesk;

import java.util.Objects;

public class KnowledgeItemExternalLink
{
    public String id;
    public String type;
    public String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeItemExternalLink)) return false;
        KnowledgeItemExternalLink that = (KnowledgeItemExternalLink) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(date, that.date);
    }
}
