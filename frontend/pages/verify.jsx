import Link from "next/link";
import Main from "layouts/main";

const NotFound = () => {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100">
      <h1 className="text-5xl text-center mt-10 mb-8">
        Verification Successful
      </h1>
      <div className="justify-center items-center flex flex-row font-semibold">
        <Link href="/login" passHref>
          <span className="hover:underline cursor-pointer">Click to Login</span>
        </Link>
      </div>
    </div>
  );
};

export default NotFound;
