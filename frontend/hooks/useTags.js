import { tagsService } from "service/tags.service";
import { useEffect, useState } from "react";
import useUser from "./useUser";

export default function useTags() {
  const user = useUser();
  const [tags, setTags] = useState([]);
  useEffect(() => {
    async function get() {
      const token = user?.token ?? null;
      setTags(await tagsService.getAll(token));
    }
    get();
  }, [user?.token, user]);
  return tags;
}
