import Link from "next/link";

export default function Navbar () {
  return (
    <div className="flex flex-row bg-black text-white w-full justify-between py-2 px-3 text-lg font-thin">
      <div className="hover:underline decoration-gray-600">
        <Link href="/">
          Smarticle
        </Link>
      </div>
      {/* <div className="hover:underline decoration-gray-600">
        <Link href="/login">
          Login
        </Link>
      </div> */}
      <div className="hover:underline decoration-gray-600">
        <Link href="/post">
          Post
        </Link>
      </div>
    </div>
  )
}