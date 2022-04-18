package se.chalmers.topdesk.model.kendra;

import java.util.Objects;

public class MetadataAccessControlList
{
    public String Name;
    public String Type;
    public String Access;

    public MetadataAccessControlList(String name, String type, String access) {
        Name = name;
        Type = type;
        Access = access;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetadataAccessControlList)) return false;
        MetadataAccessControlList that = (MetadataAccessControlList) o;
        return Objects.equals(Name, that.Name) && Objects.equals(Type, that.Type) && Objects.equals(Access, that.Access);
    }
}
