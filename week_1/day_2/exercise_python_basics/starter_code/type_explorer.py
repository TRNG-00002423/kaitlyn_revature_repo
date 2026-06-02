inty_mcintface = 60
floaty_mcfloatface = 70.99
string_mcstringface = "Steve"
bool_mcboolface = True
none_mcnoface = None

print("Variable Exploration:")
print(f"\t{'Age':<15}{'= '+str(inty_mcintface):<10}{' (type: int)':<1}")
print(f"\t{'Price':<15}{'= '+str(floaty_mcfloatface):<10}{' (type: float)':<1}")
print(f"\t{'Name':<15}{'= '+string_mcstringface:<10}{' (type: str)':<1}")
print(f"\t{'is_active':<15}{'= '+str(floaty_mcfloatface):<10}{' (type: bool)':<1}")
print(f"\t{'Result':<15}{'= '+str(none_mcnoface):<10}{' (type: NoneType)':<1}")


qa = "\"QA\""
print("\nOperators Demo:")
print(f"\t{'17 // 5':<15}{'=':<5}{17//5:<5}", "(floor division)")
print(f"\t{'17 / 5':<15}{'=':<5}{17/5:<5}", "(true division)")
print(f"\t{qa + ' * 3':<15}{'=':<5}{'QA' * 3:<5}")
print(f"\t{'True + True':<15}{'=':<5}{True + True:<5}")

print("/nPrecision Gotcha:")
print(f"\t{'0.1 + 0.2':<15}{'=':<5}{0.1 + 0.2:<5}", "(not exactly 0.3!)")