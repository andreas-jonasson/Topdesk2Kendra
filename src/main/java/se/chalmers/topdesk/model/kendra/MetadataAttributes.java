package se.chalmers.topdesk.model.kendra;

import java.util.Objects;

public class MetadataAttributes
{
    public String _category;
    public String _created_at;
    public String _data_source_id;
    public String _document_body;
    public String _document_id;
    public String _document_title;
    public String _file_type;
    public String _language_code;
    public String _last_updated_at;
    public String _source_uri;
    public String _version;
    public int    _view_count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetadataAttributes)) return false;
        MetadataAttributes that = (MetadataAttributes) o;
        return _view_count == that._view_count && Objects.equals(_category, that._category) && Objects.equals(_created_at, that._created_at) && Objects.equals(_last_updated_at, that._last_updated_at) && Objects.equals(_source_uri, that._source_uri) && Objects.equals(_version, that._version) && Objects.equals(_data_source_id, that._data_source_id) && Objects.equals(_document_body, that._document_body) && Objects.equals(_document_id, that._document_id) && Objects.equals(_document_title, that._document_title) && Objects.equals(_file_type, that._file_type) && Objects.equals(_language_code, that._language_code);
    }
}
