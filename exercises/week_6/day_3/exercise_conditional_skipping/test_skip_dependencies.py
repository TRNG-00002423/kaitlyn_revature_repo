import pytest

# try to import optional dependency
try:
    import pandas
    HAS_PANDAS = True
except ImportError:
    HAS_PANDAS = False

try:
    import numpy
    HAS_NUMPY = False
except ImportError:
    HAS_NUMPY = False

@pytest.mark.skipif(not HAS_PANDAS, reason = "pandas not installed")
def test_dataframe_operations():
    """Test requiring pandas."""
    import pandas as pd
    df = pd.DataFrame({"a": [1, 2, 3]})
    assert len(df) == 3

@pytest.mark.skipif(not HAS_NUMPY, reason="numpy not installed")
def test_numpy_operations():
    """Test requiring numpy."""
    import numpy as np
    arr = np.array([1, 2, 3])
    assert arr.sum == 6

# using importorskip
def test_with_importorskip():
    """Dynamically skip if import fails."""
    requests = pytest.importorskip("requests")
    response = requests.get("https://httpbin.org/get")