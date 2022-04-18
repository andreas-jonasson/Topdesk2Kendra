package se.chalmers.topdesk.model.kendra;

import java.util.Arrays;
import java.util.Objects;

public class Metadata
{
    public String DocumentId;
    public MetadataAttributes Attributes;
    public MetadataAccessControlList[] AccessControlList;
    public String Title;
    public String ContentType;

    public Metadata(String documentId, MetadataAttributes attributes, MetadataAccessControlList[] accessControlList, String title, String contentType) {
        DocumentId = documentId;
        Attributes = attributes;
        AccessControlList = accessControlList;
        Title = title;
        ContentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Metadata)) return false;
        Metadata metadata = (Metadata) o;
        return Objects.equals(DocumentId, metadata.DocumentId) && Objects.equals(Attributes, metadata.Attributes) && Arrays.equals(AccessControlList, metadata.AccessControlList) && Objects.equals(Title, metadata.Title) && Objects.equals(ContentType, metadata.ContentType);
    }
}
