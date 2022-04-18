package se.chalmers.topdesk.model.topdesk;

import java.util.Objects;

public class KnowledgeItemTranslationContent
{
    public String title;
    public String description;
    public String content;
    public String commentsForOperators;
    public String keywords;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeItemTranslationContent)) return false;
        KnowledgeItemTranslationContent that = (KnowledgeItemTranslationContent) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(content, that.content) && Objects.equals(commentsForOperators, that.commentsForOperators) && Objects.equals(keywords, that.keywords);
    }
}
