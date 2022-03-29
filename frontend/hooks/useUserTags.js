import { tagsService } from "service/tags.service";
import { useEffect, useState } from "react";
import useUser from "./useUser";

export default function useUserTags() {
  const user = useUser();
  const [tags, setTags] = useState([]);
  const token = user?.token;
  useEffect(() => {
    async function getUserTags() {
      setTags(await tagsService.getUserTags(token));
    }
    async function getAllTags() {
      setTags(await tagsService.getAll());
    }
    if (user?.token) {
      getUserTags();
    } else {
      getAllTags();
    }
  }, [token]);
  return tags;
}
