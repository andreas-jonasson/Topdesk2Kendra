package se.chalmers.topdesk.model.topdesk;

import java.util.Objects;

public class KnowledgeItemStatus
{
    public String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeItemStatus)) return false;
        KnowledgeItemStatus that = (KnowledgeItemStatus) o;
        return Objects.equals(id, that.id);
    }
}
