import { serverUrl } from "helpers/api";

async function getAllTags() {
  let data;
  try {
    data = await (
      await fetch(`${serverUrl}/tag/retriveTags`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      })
    ).json();
  } catch (err) {
    throw new Error(err);
  }
  return data;
}

export const tagsService = {
  getAllTags,
};
