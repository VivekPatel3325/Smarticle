import { tagsService } from "service/tags.service";
import { useEffect, useState } from "react";
import useUser from "./useUser";

export default function useUserTags() {
  const user = useUser();
  const [tags, setTags] = useState([]);
  const token = user?.token;
  useEffect(() => {
    async function get() {
      setTags(await tagsService.getUserTags(token));
    }
    get();
  }, [token]);
  return tags;
}
