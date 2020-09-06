# frozen_string_literal: true

require_relative 'lib/skippy/version'

Gem::Specification.new do |spec|
  spec.name          = 'skippy'
  spec.version       = Skippy::VERSION
  spec.authors       = ['Aufauvre, Jean-Alexis']
  spec.email         = ['jean-alexis.aufauvre@outlook.com']

  spec.summary       = 'Will Skippy Get Home?'
  spec.homepage      = 'https://github.com/jaaufauvre/will-skippy-get-home'
  spec.required_ruby_version = Gem::Requirement.new('>= 2.7.0')

  # Specify which files should be added to the gem when it is released.
  # The `git ls-files -z` loads the files in the RubyGem that have been added into git.
  spec.files = Dir.chdir(File.expand_path(__dir__)) do
    `git ls-files -z`.split("\x0").reject { |f| f.match(%r{^(test|spec|features)/}) }
  end
  spec.bindir        = 'exe'
  spec.executables   = spec.files.grep(%r{^exe/}) { |f| File.basename(f) }
  spec.require_paths = ['lib']
end
