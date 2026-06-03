class TestCase:
    """Represents a single test case.

    Class Attributes:
        total_created (int): Count of all TestCase objects ever created

    Instance Attributes:
        name (str): Test name (e.g., "test_login_valid")
        description (str): What this test verifies
        priority (str): "high", "medium", or "low" (default: "medium")
        tags (list): Labels like ["smoke", "regression"]
    """
    total_created = 0

    # TODO: Implement __init__, run(), and a class method
    def __init__(self, name, priority, description="", tags = []):
        self.name = name
        self.description = description
        self.priority = priority
        self.tags = tags
        TestCase.total_created += 1

    def run(self):
        """Simulate running the test. Return True for pass, False for fail.
        For now, use: return "fail" not in self.name
        """
        return "fail" not in self.name

    @classmethod
    def from_dict(cls, data):
        """Create a TestCase from a dictionary.
        Example: TestCase.from_dict({"name": "test_login", "priority": "high"})
        """

        # name and priority are required, description and tags have default blank values
        if "description" in data and "tags" in data:
            return cls(data["name"], data["priority"], data["description"], data["tags"])
        elif "description" in data:
            return cls(data["name", data["priority"], data["description"]])
        elif "tags" in data:
            return cls(data["name"], data["priority"], tags=data["tags"])
        else:
            pass


    @staticmethod
    def is_valid_name(name):
        """Check if name starts with 'test_' and has no spaces."""
        return " " in name and name[:5] == "test_"
    
class TestResult:
    """The outcome of running a single test.

    Instance Attributes:
        test_name (str): Which test was run
        status (str): "pass" or "fail"
        duration_ms (float): How long it took
        error_message (str or None): Error details if failed
    """
    def __init__(self, test_name, duration_ms, error_message):
        self.test_name = test_name
        self.duration_ms = duration_ms
        self.error_message = error_message

    def summary(self):
        """Return a one-line summary like: '✅ test_login (120ms)'"""
        if self.error_message is None:
            return f"✅ {self.test_name} ({self.duration_ms}ms)"
        else:
            return f"❌ {self.test_name} ({self.duration_ms}ms ({self.error_message}))"
class TestSuite:
    """A collection of test cases.

    Instance Attributes:
        name (str): Suite name
        tests (list): List of TestCase objects

    Methods:
        add_test(test): Add a TestCase
        remove_test(name): Remove by name
        get_by_priority(priority): Return tests matching the priority
        count(): Return number of tests
    """
    def __init__(self, name, tests):
        self.name = name
        self.tests = tests
    
    def add_test(self, test):
        self.tests.append(test)
    
    def remove_test(self, name):
        idx_to_remove = None
        for i, test in self.tests:
            if test.name == name:
                idx_to_remove = i
                break
        self.tests.pop(idx_to_remove)

    def get_by_priority(self, priority):
        priority_tests = []
        for test in self.tests:
            if self.priority == priority:
                priority_tests.append(test)
        return priority_tests
    
    def count(self):
        return len(self.tests)