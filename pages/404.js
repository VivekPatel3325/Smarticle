import Link from "next/link";

const NotFound = () => {
  return (
    <div>
      <div className="not-found">
        <p className="font-bold text-5xl mb-2">404 Error</p>
        <p className="font-semibold text-2xl mb-2">Page Not Found</p>
        <Link href="/">
          <a>Back to Home Page</a>
        </Link>
      </div>
    </div>
  );
};

export default NotFound;
