import sys
import platform
import pytest


@pytest.mark.skipif(
    sys.platform != "win32",
    reason = "Windows-specific functionality"
)
def test_windows_registry():
    """Test Windows registry operations."""
    # windows-specific test code
    import winreg
    assert True

@pytest.mark.skipif(
    sys.platform != "linux",
    reason = "Linux-specific functionality"
)
def test_linux_permissions():
    """Test Linux file permissions."""
    import pwd
    # Linux-specific test code
    assert True

@pytest.mark.skipif(
    sys.platform != "darwin",
    reason = "macOS-specific functionality"
)
def test_macos_keychain():
    """Test macOS Keychain access."""
    # macOS-specific code.
    assert True

@pytest.mark.skipif(
    platform.machine() != "x86_64",
    reason = "Requires x86_64 architecture."
)
def test_x86_specific():
    """Test that requires specific CPU architecture."""
    assert True

@pytest.mark.skipif(
    platform.machine() != "arm64",
    reason = "Requires arm64 architecture."
)
def test_arm64_specific():
    """Test that requires specific CPU architecture."""
    assert True