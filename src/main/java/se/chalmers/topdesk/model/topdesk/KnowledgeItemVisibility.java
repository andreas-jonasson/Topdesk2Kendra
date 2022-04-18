package se.chalmers.topdesk.model.topdesk;

import java.util.Objects;

public class KnowledgeItemVisibility
{
    public String sspVisibility;
    public String sspVisibleFrom;
    public String sspVisibleUntil;
    public boolean sspVisibilityFilteredOnBranches;
    public boolean operatorVisibilityFilteredOnBranches;
    public boolean openKnowledgeItem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnowledgeItemVisibility)) return false;
        KnowledgeItemVisibility that = (KnowledgeItemVisibility) o;
        return sspVisibilityFilteredOnBranches == that.sspVisibilityFilteredOnBranches && operatorVisibilityFilteredOnBranches == that.operatorVisibilityFilteredOnBranches && openKnowledgeItem == that.openKnowledgeItem && Objects.equals(sspVisibility, that.sspVisibility) && Objects.equals(sspVisibleFrom, that.sspVisibleFrom) && Objects.equals(sspVisibleUntil, that.sspVisibleUntil);
    }
}
