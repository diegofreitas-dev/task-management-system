package service;

import model.Tag;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagService {
    private Map<String, Tag> tagsByName;
    private Map<Integer, Tag> tagsById;
    private int countID = 1;

    public TagService() {
        tagsByName = new HashMap<>();
        tagsById = new HashMap<>();
    }

    public Tag createOrGetTag(String name) {

        String normalized = name.toLowerCase();

        Tag existing = tagsByName.get(normalized);

        if (existing != null) {
            return existing;
        }

        Tag tag = new Tag(countID, normalized);

        tagsByName.put(normalized, tag);
        tagsById.put(countID, tag);
        countID++;

        return tag;
    }

    public Tag updateTag(int id, String name) {
        Tag tag = getTagById(id);

        String oldName = tag.getName();
        String normalized = name.toLowerCase();

        if (tagsByName.containsKey(normalized)) {
            throw new IllegalArgumentException("Tag name already exist");
        }

        tagsByName.remove(oldName);

        tag.setName(normalized);

        tagsByName.put(normalized, tag);

        return tag;
    }

    public Tag deleteTag(int id) {
        Tag tag = getTagById(id);
        String tagName = tag.getName();

        tagsByName.remove(tagName);
        tagsById.remove(id);

        return tag;
    }

    public Collection<Tag> getTags() {
        return tagsByName.values();
    }

    private Tag getTagById(int id) {
        Tag tag = tagsById.get(id);

        if(tag == null) {
            throw new IllegalArgumentException("Invalid tag id, tag not exist");
        }

        return tag;
    }

    private Tag getTagByName(String tagName) {
        Tag tag = tagsByName.get(tagName.toLowerCase());

        if (tag == null) {
            throw new IllegalArgumentException("invalid tag name, tag not exist");
        }

        return tag;
    }
}
