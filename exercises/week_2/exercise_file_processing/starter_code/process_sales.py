"""
Week 2 Exercise — CSV processing with context managers.

TODO:
1. Read starter_code/data/sales.csv using csv.DictReader and with open(...).
2. Compute rows count, grand total (sum of units * unit_price), average line revenue.
3. Find SKU with max line revenue (tie: first in file).
4. Write output/summary.txt using with open(..., "w", encoding="utf-8").
"""

from __future__ import annotations
import csv

def main() -> None:
    # Your implementation here

    sales = []
    with open("data/sales.csv") as file:
        reader = csv.DictReader(file)
        sales 
        for row in reader:
            print(row)
            if ""
            sales.append(row)

    # raise NotImplementedError("Complete this exercise")


if __name__ == "__main__":
    main()