import { authorsService } from "service/authors.service";
import { useEffect, useState } from "react";

export default function useAllAuthors() {
  const [authors, setAuthors] = useState([]);
  useEffect(() => {
    async function get() {
      setAuthors(await authorsService.getAuthors());
    }
    get();
  }, []);
  return authors;
}
