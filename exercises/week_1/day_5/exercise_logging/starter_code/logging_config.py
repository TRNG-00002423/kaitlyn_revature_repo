"""
Configure logging for the QA Test Framework.

Requirements:
1. Console handler: Show INFO and above, concise format
2. File handler: Capture DEBUG and above, detailed format with timestamps
3. Use a RotatingFileHandler (max 1MB, keep 3 backups)
"""

import logging
from logging.handlers import RotatingFileHandler
import sys

def setup_logging(log_file="test_framework.log", console_level=logging.INFO):
    """Configure and return the root logger for the framework.

    Args:
        log_file: Path to the log file
        console_level: Minimum level for console output

    Returns:
        logging.Logger configured with both handlers
    """
    # TODO: Create logger named "qa_framework"
    qa_frameworks = logging.getLogger("qa_frameworks")
    qa_frameworks.setLevel(console_level)
    # TODO: Add StreamHandler for console (INFO+)
    console_handler = logging.StreamHandler(stream=sys.stdout)
    console_handler.setLevel(console_level)
    # TODO: Add RotatingFileHandler for file (DEBUG+)
    file_handler = RotatingFileHandler(log_file)
    file_handler.setLevel(logging.DEBUG)
    # TODO: Set appropriate formatters on each handler
    console_handler.setFormatter(logging.Formatter("%(levelname)-8s | %(message)s"))
    file_handler.setFormatter(logging.Formatter("%(levelname)-8s | %(message)s"))
    # TODO: Return the configured logger
    qa_frameworks.addHandler(console_handler)
    qa_frameworks.addHandler(file_handler)
    return qa_frameworks
    
