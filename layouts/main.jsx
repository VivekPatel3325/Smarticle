import Navbar from "components/Navbar"
import Footer from "components/Footer"
export default function MainCentered ({children, title}) {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center">
    <Navbar />
    <div className="flex-1 sm:px-32 px-12">
      {
        title &&
        <div className="font-bold text-2xl py-6">
          {title}
        </div>
      }
      {children}
    </div>
    <Footer />
    </div>
  )
}
