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