package se.chalmers.topdesk.model.topdesk;

import java.util.Objects;

public class KnowledgeItemTranslationCreator
{
    public String id;
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeItemTranslationCreator)) return false;
        KnowledgeItemTranslationCreator that = (KnowledgeItemTranslationCreator) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }
}
