package com.github.ajalt.clikt.completion

@Suppress("unused")
class FishCompletionTest : CompletionTestBase("fish") {
    override fun `custom completions expected`(): String {
        return """
        |# Command completion for c
        |# Generated by Clikt
        |
        |## Options for c
        |complete -c c -l o -r -fa "(echo foo bar)"
        |complete -c c -s h -l help -d 'Show this message and exit'
        |
        |## Arguments for c
        |complete -c c -fa (echo zzz xxx)
        |
        """
    }

    override fun `subcommands with multi-word names expected`(): String {
        return """
        |# Command completion for c
        |# Generated by Clikt
        |
        |
        |### Setup for c
        |set -l c_subcommands 'sub sub-command'
        |
        |## Options for c
        |complete -c c -n "not __fish_seen_subcommand_from ${'$'}c_subcommands" -s h -l help -d 'Show this message and exit'
        |
        |
        |### Setup for sub
        |complete -c c -f -n __fish_use_subcommand -a sub 
        |
        |## Options for sub
        |complete -c c -n "__fish_seen_subcommand_from sub" -s h -l help -d 'Show this message and exit'
        |
        |
        |### Setup for sub-command
        |set -l c_sub_command_subcommands 'sub-sub long-sub-command'
        |complete -c c -f -n __fish_use_subcommand -a sub-command 
        |
        |## Options for sub-command
        |complete -c c -n "__fish_seen_subcommand_from sub-command" -s h -l help -d 'Show this message and exit'
        |
        |
        |### Setup for sub-sub
        |complete -c c -f -n "__fish_seen_subcommand_from sub-command; and not __fish_seen_subcommand_from ${'$'}c_sub_command_subcommands" -a sub-sub 
        |
        |## Options for sub-sub
        |complete -c c -n "__fish_seen_subcommand_from sub-sub" -s h -l help -d 'Show this message and exit'
        |
        |
        |### Setup for long-sub-command
        |complete -c c -f -n "__fish_seen_subcommand_from sub-command; and not __fish_seen_subcommand_from ${'$'}c_sub_command_subcommands" -a long-sub-command 
        |
        |## Options for long-sub-command
        |complete -c c -n "__fish_seen_subcommand_from long-sub-command" -s h -l help -d 'Show this message and exit'
        |
        """
    }

    override fun `option secondary names expected`(): String {
        return """
        |# Command completion for c
        |# Generated by Clikt
        |
        |## Options for c
        |complete -c c -l flag -l no-flag
        |complete -c c -s h -l help -d 'Show this message and exit'
        |
        """
    }

    override fun `explicit completion candidates expected`(): String {
        return """
        |# Command completion for c
        |# Generated by Clikt
        |
        |## Options for c
        |complete -c c -l none -r
        |complete -c c -l path -r -F
        |complete -c c -l host -r -fa "(__fish_print_hostnames)"
        |complete -c c -l user -r -fa "(__fish_complete_users)"
        |complete -c c -l fixed -r -fa "foo bar"
        |
        |## Arguments for c
        |complete -c c -fa "(__fish_complete_users)"
        |complete -c c -fa "baz qux"
        |
        """
    }
}