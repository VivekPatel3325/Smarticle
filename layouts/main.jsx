export default function MainCentered ({children, title}) {
  return (
    <div className="h-screen flex flex-col items-center justify-center">
    <div>Navbar</div>
    <div className="flex-1">
      {title && <div className="font-bold text-2xl py-6">{title}</div>}
      {children}
    </div>
    <div>Footer</div>
    </div>
  )
}
