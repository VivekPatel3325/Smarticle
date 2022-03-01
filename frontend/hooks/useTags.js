import {tagsService} from "service/tags.service";
import { useEffect, useState } from "react";

export default function useTags () {
  const [tags, setTags] = useState([]);
  useEffect(() => {
    const tags = tagsService.getAll();
    setTags(tags);
  }, []);
  return tags;
}
