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
import sys
from functools import reduce

def main() -> None:
    # Your implementation here

    sales = []
    bad_rows = 0
    with open("data/sales.csv", "r") as file:
        reader = csv.DictReader(file)
        sales 
        for row in reader:
            try:
                sku = row["sku"]
                units = int(row["units"])
                unit_price = float(row["unit_price"])
                sales.append({"sku": sku, "units": units, "unit_price": unit_price})
            except:
                bad_rows += 1
        if bad_rows > 0:
            sys.stderr.write(f"bad rows: {bad_rows}")
    
    # output goes to starter_code/output/summary.txt
    with open('output/summary.txt', "w") as file:
        rows = len(sales)
        grand_total = reduce(lambda a, b: a + b["units"] * b["unit_price"], sales, 0)
        average_line_revenue = reduce(lambda a, b: a+(b["units"]*b["unit_price"])/len(sales), sales, 0)
        top_line_revenue = 0
        top_sku = sales[0]["sku"]
        for sale in sales:
            if sale["units"]*sale["unit_price"] > top_line_revenue:
                top_line_revenue = sale["units"]*sale["unit_price"]
                top_sku = sale["sku"]
        file.write(f"rows={rows}\n")
        file.write(f"grand_total={grand_total:.2f}\n")
        file.write(f"average_line_revenue={average_line_revenue:.2f}\n")
        file.write(f"top_line_revenue={top_line_revenue:.2f}\n")
        file.write(f"top_sku={top_sku}\n")
        

    # raise NotImplementedError("Complete this exercise")


if __name__ == "__main__":
    main()