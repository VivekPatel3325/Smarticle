import { postService } from "service/post.service";
import { useEffect, useState } from "react";

export default function usePosts() {
  const [posts, setPosts] = useState([]);
  useEffect(()=> {
    const p = postService.getAll();
    setPosts(p);
  }, []);
  return posts;
}
