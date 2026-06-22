from os import getenv

from dotenv import load_dotenv
from sqlalchemy import create_engine, text
import pandas as pd


load_dotenv()
CS = getenv("CS")
engine = create_engine(CS)

query = "SELECT * FROM employees;"
df = pd.read_sql(query, engine)
print(df)

df.to_sql(
    name = "processed",
    con=engine,
    if_exists='replace',
    index=False
)

first_name = input("first name: ")
last_name = input("last name: ")
email = input("email: ")
hire_date = input("hire date (YYYY-MM-DD): ")
salary = float(input("salary: "))

with engine.begin() as conn:
    result = conn.execute(
        text("""
        INSERT INTO employees (
             first_name,
             last_name,
             email,
             hire_date,
             salary)
             VALUES (
             :first_name,
             :last_name,
             :email,
             :hire_date,
             :salary)
             RETURNING employee_id
        """
                
        ),     {
                "first_name": first_name,
                "last_name": last_name,
                "email": email,
                "hire_date": hire_date,
                "salary": salary
            }

    )
    employee_id = result.scalar()
    print("Employee " + str(employee_id) + " inserted successfully.")