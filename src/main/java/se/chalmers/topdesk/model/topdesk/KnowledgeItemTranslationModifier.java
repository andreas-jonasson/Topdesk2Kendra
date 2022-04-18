package se.chalmers.topdesk.model.topdesk;

import java.util.Objects;

public class KnowledgeItemTranslationModifier
{
    public String id;
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeItemTranslationModifier)) return false;
        KnowledgeItemTranslationModifier that = (KnowledgeItemTranslationModifier) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }
}
