import useUser from "hooks/useUser";
import Main from "layouts/main";
import { useState, useEffect } from "react";
import apiUrl from "helpers/api";

const baseUrl = `${apiUrl}/getPosts`;

export default function Home () {
  const [posts, setPosts] = useState([]);
  const user = useUser();
  const fetchPosts = async () => {
    const res = await (await fetch(baseUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${user?.token}`,
      },
      credentials: 'include',
    })).json();
    setPosts(res.posts);
  }
  useEffect(() => {
    if (user?.token) fetchPosts();
  }, [user?.token]);
  return (
    <Main title="Home">
      {
        posts.map((post) =>
        <div key={post.id} className="border-2 border-black my-2 p-2">
          <div>
          {post.title}
          </div>
          <div>
            {post.post}
          </div>
        </div>)
      }
    </Main>
  )
}