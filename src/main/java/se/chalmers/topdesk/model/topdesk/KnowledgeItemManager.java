package se.chalmers.topdesk.model.topdesk;

import java.util.Objects;

public class KnowledgeItemManager
{
    public String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeItemManager)) return false;
        KnowledgeItemManager that = (KnowledgeItemManager) o;
        return Objects.equals(id, that.id);
    }
}
