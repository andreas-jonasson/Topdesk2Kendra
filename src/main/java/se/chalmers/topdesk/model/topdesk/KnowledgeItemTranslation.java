package se.chalmers.topdesk.model.topdesk;

import java.util.Objects;

public class KnowledgeItemTranslation
{
    public String language;
    public KnowledgeItemTranslationContent content;
    public KnowledgeItemTranslationCreator creator;
    public String creationDate;
    public KnowledgeItemTranslationModifier modifier;
    public String modificationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeItemTranslation)) return false;
        KnowledgeItemTranslation that = (KnowledgeItemTranslation) o;
        return Objects.equals(language, that.language) && Objects.equals(content, that.content) && Objects.equals(creator, that.creator) && Objects.equals(creationDate, that.creationDate) && Objects.equals(modifier, that.modifier) && Objects.equals(modificationDate, that.modificationDate);
    }
}
