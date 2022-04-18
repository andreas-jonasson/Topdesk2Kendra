package se.chalmers.topdesk.model.topdesk;

import java.util.Objects;

public class KnowledgeItemParent
{
    public String id;
    public String number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnowledgeItemParent that = (KnowledgeItemParent) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }
}
